import { Request, Response, NextFunction } from "express";
import { registerUser, loginUser } from "./auth.service";

// REGISTER
export const register = async (
  req: Request,
  res: Response,
  next: NextFunction
) => {
  try {
    const { name, email, password } = req.body;

    const user = await registerUser(name, email, password);

    res.status(201).json({
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

    res.status(200).json(result);
  } catch (error) {
    next(error);
  }
};