import { Router } from "express";
import { HomeController } from "./home.controller";

const router = Router();
const homeController = new HomeController();

router.get("/", homeController.getHome);

export default router;