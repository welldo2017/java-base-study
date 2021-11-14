package com.welldo.zero.a4_oop.base;

/**
 * 多态性
 *
 * 多态的特性就是，运行期才能动态决定调用的子类方法。
 * 。这种不确定性的方法调用，究竟有什么作用？
 *
 *
 * @author welldo
 * @date 2020/8/8
 */
public class Plymorphic6 {
    public static void main(String[] args) {
        // 给一个有普通收入、工资收入和享受国务院特殊津贴的小伙伴算税:
        Income[] incomes = new Income[] {
                new Income(3000),
                new Salary(7500),
                new StateCouncilSpecialAllowance(15000)
        };
        System.out.println(totalTax(incomes));
    }


    //现在，我们要编写一个报税的财务软件，对于一个人的所有收入进行报税，可以这么写：
    public static double totalTax(Income... incomes) {
        double total = 0;
        for (Income income: incomes) {
            total = total + income.getTax();
        }
        return total;
    }


}

/**
 * 此种收入,无论多少, 税率 10%
 */
class Income {
    protected double income;

    public Income(double i) {
        this.income = i;
    }

    public double getTax() {
        return income * 0.1; // 税率10%
    }
}

// 对于工资收入，超过某基数的部分,税率0.2
class Salary extends Income {

    public Salary(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        if (income <= 5000) {
            return 0;
        }
        return (income - 5000) * 0.2;
    }
}

//享受国务院特殊津贴，那么按照规定，可以全部免税：
class StateCouncilSpecialAllowance extends Income {

    public StateCouncilSpecialAllowance(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        return 0.0;
    }
}