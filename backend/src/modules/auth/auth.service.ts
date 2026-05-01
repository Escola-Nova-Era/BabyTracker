import { prisma } from "../../lib/prisma";
import bcrypt from "bcrypt";
import jwt from "jsonwebtoken";

const JWT_SECRET = "supersecret";
const RESET_SECRET = "resetsecret";
const RESET_EXPIRES_IN = "15m";

// REGISTER
export const registerUser = async (
  name: string,
  email: string,
  password: string
) => {
  const existingUser = await prisma.user.findUnique({
    where: { email },
  });

  if (existingUser) {
    throw new Error("User already exists");
  }

  const hashedPassword = await bcrypt.hash(password, 10);

  const user = await prisma.user.create({
    data: {
      name,
      email,
      password: hashedPassword,
    },
  });

  return user;
};

// LOGIN
export const loginUser = async (email: string, password: string) => {
  const user = await prisma.user.findUnique({
    where: { email },
  });

  if (!user) {
    throw new Error("Invalid credentials");
  }

  const isPasswordValid = await bcrypt.compare(password, user.password);

  if (!isPasswordValid) {
    throw new Error("Invalid credentials");
  }

  const token = jwt.sign({ userId: user.id }, JWT_SECRET, {
    expiresIn: "7d",
  });

  return {
    token,
    user: {
      id: user.id,
      name: user.name,
      email: user.email,
    },
  };
};

// FORGOT PASSWORD
export const forgotPassword = async (email: string) => {
  const user = await prisma.user.findUnique({
    where: { email },
  });

  if (!user) {
    return {
      message: "If the email exists, a reset link has been sent",
    };
  }

  const token = jwt.sign(
    { userId: user.id },
    RESET_SECRET,
    { expiresIn: RESET_EXPIRES_IN }
  );

  return {
    message: "If the email exists, a reset link has been sent",
    resetToken: token,
  };
};

// RESET PASSWORD
export const resetPassword = async (
  token: string,
  newPassword: string
) => {
  try {
    const decoded = jwt.verify(token, RESET_SECRET) as { userId: string };

    const hashedPassword = await bcrypt.hash(newPassword, 10);

    await prisma.user.update({
      where: { id: decoded.userId },
      data: {
        password: hashedPassword,
      },
    });

    return { message: "Password updated successfully" };
  } catch {
    throw new Error("Invalid or expired token");
  }
};