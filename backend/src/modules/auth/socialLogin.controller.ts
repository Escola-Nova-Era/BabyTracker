import { Request, Response, NextFunction } from "express";
import { googleLogin } from "./socialLogin.service";

export const googleAuth = async (
  req: Request,
  res: Response,
  next: NextFunction
) => {
  try {
    const { token } = req.body;

    if (!token) {
      throw { status: 400, message: "Token is required" };
    }

    const result = await googleLogin(token);

    res.status(200).json(result);
  } catch (error) {
    next(error);
  }
};