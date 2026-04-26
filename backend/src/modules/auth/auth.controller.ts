import { Request, Response, NextFunction } from "express";
import {
  registerUser,
  loginUser,
  forgotPassword,
  resetPassword,
} from "./auth.service";

// REGISTER
export const register = async (
  req: Request,
  res: Response,
  next: NextFunction
) => {
  try {
    const { name, email, password } = req.body;

    const user = await registerUser(name, email, password);

    return res.status(201).json({
      id: user.id,
      name: user.name,
      email: user.email,
    });
  } catch (error) {
    next(error);
  }
};

// LOGIN
export const login = async (
  req: Request,
  res: Response,
  next: NextFunction
) => {
  try {
    const { email, password } = req.body;

    const result = await loginUser(email, password);

    return res.status(200).json(result);
  } catch (error) {
    next(error);
  }
};

// FORGOT PASSWORD
export const forgotPasswordController = async (
  req: Request,
  res: Response,
  next: NextFunction
) => {
  try {
    const { email } = req.body;

    const result = await forgotPassword(email);

    return res.status(200).json(result);
  } catch (error) {
    next(error);
  }
};

// RESET PASSWORD
export const resetPasswordController = async (
  req: Request,
  res: Response,
  next: NextFunction
) => {
  try {
    const { token, newPassword } = req.body;

    const result = await resetPassword(token, newPassword);

    return res.status(200).json(result);
  } catch (error) {
    next(error);
  }
};