import { Request, Response, NextFunction } from "express";
import { loginUser } from "./auth.service";

export const login = async (
  req: Request,
  res: Response,
  next: NextFunction
) => {
  try {
    const { email, password } = req.body;

    if (!email || !password) {
      throw { status: 400, message: "Email and password are required" };
    }

    const result = await loginUser(email, password);

    res.status(200).json(result);
  } catch (error) {
    next(error);
  }
};