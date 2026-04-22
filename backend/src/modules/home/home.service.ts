import { prisma } from "../../lib/prisma";
import { getInsights } from "../insights/insights.service";

type HomeResponse = {
  user: {
    name: string;
  };
  summary: {
    totalActivities: number;
  };
  lastActivity: {
    type: string;
    date: Date;
  } | null;
};

export const getHomeData = async (userId: string): Promise<HomeResponse> => {
  const user = await prisma.user.findUnique({
    where: { id: userId },
    select: { name: true },
  });

  if (!user) {
    throw { status: 404, message: "User not found" };
  }

  const insights = await getInsights(userId);

  return {
    user: {
      name: user.name,
    },
    summary: {
      totalActivities: insights.totalActivities,
    },
    lastActivity: insights.lastActivity
      ? {
          type: insights.lastActivity.type,
          date: insights.lastActivity.createdAt,
        }
      : null,
  };
};