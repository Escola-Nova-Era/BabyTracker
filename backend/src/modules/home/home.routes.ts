import { Router } from "express";
import { HomeController } from "./home.controller";

const router = Router();
const homeController = new HomeController();

router.get("/", (req, res) => homeController.getHome(req, res));

export default router;