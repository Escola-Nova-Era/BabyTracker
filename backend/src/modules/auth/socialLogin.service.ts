import { OAuth2Client } from "google-auth-library";
import { prisma } from "../../lib/prisma";
import jwt from "jsonwebtoken";
import { env } from "../../config/env";

const client = new OAuth2Client(env.googleClientId);

export const googleLogin = async (token: string) => {
  const ticket = await client.verifyIdToken({
    idToken: token,
    audience: env.googleClientId,
  });

  const payload = ticket.getPayload();

  if (!payload || !payload.email) {
    throw { status: 401, message: "Invalid Google token" };
  }

  const { email, name } = payload;

  let user = await prisma.user.findUnique({
    where: { email },
  });

  if (!user) {
    user = await prisma.user.create({
      data: {
        name: name || "Google User",
        email,
        password: "",
      },
    });
  }

  const tokenJwt = jwt.sign(
    { userId: user.id },
    env.jwtSecret,
    { expiresIn: "7d" }
  );

  return {
    token: tokenJwt,
    user: {
      id: user.id,
      name: user.name,
      email: user.email,
    },
  };
};