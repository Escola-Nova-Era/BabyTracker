import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';
import { prisma } from '../../lib/prisma';

// Contrato exclusivo para o Registro 
interface RegisterDTO {
    name: string; 
    email: string;
    password: string;
}

// Contrato exclusivo para o Login 
interface LoginDTO {
    email: string;
    password: string;
}

export class AuthService {
    async register({ name, email, password }: RegisterDTO) {
        
        const userExists = await prisma.user.findUnique({ where: { email } });
        if (userExists) {
            throw new Error('Email já registrado');
        }

        const hashedPassword = await bcrypt.hash(password, 10);
        if (!name) throw new Error('Nome é obrigatório para registro');

        const user = await prisma.user.create({
            data: { name, email, password: hashedPassword },
        });

        return { id: user.id, name: user.name, email: user.email };
    }

    // O Login aceita apenas email e senha
    async login({ email, password }: LoginDTO) {
        const user = await prisma.user.findUnique({ where: { email } });
        if (!user) {
            throw new Error('Email ou senha inválidos');
        }

        const isPasswordValid = await bcrypt.compare(password, user.password);
        if (!isPasswordValid) {
            throw new Error('Email ou senha inválidos');
        }

        const secret = process.env.JWT_SECRET;
        if (!secret) {
            throw new Error('Chave secreta para JWT não configurada');
        }

        const token = jwt.sign({ userId: user.id }, secret, { expiresIn: '1d' });
        
        return { 
            user: { id: user.id, name: user.name, email: user.email },
            token 
        };
    }   
}