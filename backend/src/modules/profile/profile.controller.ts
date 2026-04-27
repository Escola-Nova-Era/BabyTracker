import { Request, Response, NextFunction } from "express";
import { getProfile } from "./profile.service";

export const getProfileController = async (
  req: Request,
  res: Response,
  next: NextFunction
) => {
  try {
    const userId = (req as any).user?.userId;

    if (!userId) {
      return res.status(401).json({ message: "Unauthorized" });
    }

    const user = await getProfile(userId);

    return res.status(200).json(user);
  } catch (error) {
    if (error instanceof Error && error.message === "User not found") {
      return res.status(404).json({ message: error.message });
    }

    next(error);
  }
};