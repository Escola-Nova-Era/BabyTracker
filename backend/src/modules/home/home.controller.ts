import { Request, Response, NextFunction } from "express";
import { getHomeData } from "./home.service";

export const getHome = async (
  req: Request,
  res: Response,
  next: NextFunction
) => {
  try {
    const userId = req.user.userId;

    const data = await getHomeData(userId);

    res.status(200).json(data);
  } catch (error) {
    next(error);
  }
};