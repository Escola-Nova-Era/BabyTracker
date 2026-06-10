import { prisma } from "../../lib/prisma";

interface ProfileResponse {
  id: string;
  name: string;
  email: string;
}

export const getProfile = async (userId: string): Promise<ProfileResponse> => {
  const user = await prisma.user.findUnique({
    where: { id: userId },
    select: {
      id: true,
      name: true,
      email: true,
    },
  });

  if (!user) {
    throw new Error("User not found");
  }

  return user;
};