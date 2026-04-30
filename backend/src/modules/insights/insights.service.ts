import { prisma } from "../../lib/prisma";

export const getInsights = async (userId: string) => {
  const babies = await prisma.baby.findMany({
    where: { userId },
    select: { id: true },
  });

  const ids = babies.map((b) => b.id);

  const total = await prisma.activity.count({
    where: { babyId: { in: ids } },
  });

  const byType = await prisma.activity.groupBy({
    by: ["type"],
    where: { babyId: { in: ids } },
    _count: true,
  });

  const last = await prisma.activity.findFirst({
    where: { babyId: { in: ids } },
    orderBy: { createdAt: "desc" },
  });

  return { totalActivities: total, activitiesByType: byType, lastActivity: last };
};