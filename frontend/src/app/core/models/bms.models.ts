export interface EmployeeDailyCount {
  id?: number;
  date: string;
  day?: string;
  employeeCount: number;
}

export interface FruitDailyCount {
  id?: number;
  date: string;
  day?: string;
  apple: number;
  banana: number;
  pear: number;
  orange: number;
  guava: number;
}

export interface GarbageDailyCount {
  id?: number;
  date: string;
  day?: string;
  paper: number;
  plastic: number;
  glass: number;
  metal: number;
  wood: number;
  eWaste: number;
  organicFood: number;
}

export interface DashboardSummary {
  todayEmployees: number;
  todayFruitKg: number;
  todayGarbageKg: number;
  monthlyAverageEmployees: number;
  monthlyFruitKg: number;
  monthlyGarbageKg: number;
}
