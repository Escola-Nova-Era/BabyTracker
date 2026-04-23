import { prisma } from "../../lib/prisma";

type HomeResponse = {
  user: {
    name: string;
  };
  summary: {
    totalActivities: number;
  };
  lastActivity: any | null;
};

export const getHomeData = async (userId: string): Promise<HomeResponse> => {
  const user = await prisma.user.findUnique({
    where: { id: userId },
    select: { name: true },
  });

  if (!user) {
    throw { status: 404, message: "User not found" };
  }

  return {
    user: {
      name: user.name,
    },
    summary: {
      totalActivities: 0, // placeholder por enquanto
    },
    lastActivity: null, // placeholder por enquanto
  };
};