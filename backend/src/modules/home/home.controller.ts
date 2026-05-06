import { Request, Response } from "express";

export class HomeController {
  public getHome(req: Request, res: Response): Response {
    return res.status(200).json({
      message: "Welcome to BabyTracker API 🚀",
      status: "running",
    });
  }
}