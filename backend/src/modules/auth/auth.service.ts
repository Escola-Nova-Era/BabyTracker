import { prisma } from "../../lib/prisma";
import bcrypt from "bcrypt";
import jwt from "jsonwebtoken";
import { env } from "../../config/env";

export const loginUser = async (email: string, password: string) => {
  // 1. Buscar usuário
  const user = await prisma.user.findUnique({
    where: { email },
  });

  if (!user || !user.password) {
    throw { status: 401, message: "Invalid credentials" };
  }

  // 2. Comparar senha
  const isPasswordValid = await bcrypt.compare(password, user.password);

  if (!isPasswordValid) {
    throw { status: 401, message: "Invalid credentials" };
  }

  // 3. Gerar token
  const token = jwt.sign(
    { userId: user.id },
    env.jwtSecret,
    { expiresIn: "7d" }
  );

  // 4. Retornar resposta
  return {
    token,
    user: {
      id: user.id,
      name: user.name,
      email: user.email,
    },
  };
};