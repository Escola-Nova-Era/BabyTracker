import { Response, NextFunction } from "express";
import { getInsights } from "./insights.service";

export const insights = async (req: any, res: Response, next: NextFunction) => {
  try {
    const data = await getInsights(req.user.userId);
    res.json(data);
  } catch (error) {
    next(error);
  }
};