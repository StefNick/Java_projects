import java.util.*;

public class Polynom
{
    int[] coeff;
    int degree;

    Polynom(int n)
    {
        degree = n;
        coeff = new int[degree + 1];
        for(int i = 0; i < degree + 1; i++)
        {
            coeff[i] = 0;
        }
    }
    void inputCoeff()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter coefficient: ");
        for(int i = 0; i < degree + 1; i++)
        {
            System.out.println("coeff[" + i + "] = ");
            coeff[i] = in.nextInt();
            System.out.println();
        }
    }
    void setCoeff(int index, int x)
    {
        coeff[index] = x;
    }
    int getCoeff(int index)
    {
        return coeff[index];
    }
    int getDegree()
    {
        return  degree;
    }
    private void output()
    {
        for(int i = 0; i < degree + 1; i++)
        {
            System.out.printf("%d*X(%d)",coeff[i],i);
            if(i < degree)
                System.out.print("+");
        }
        System.out.println();

    }
    private static Polynom inputPolynom()
    {
        int degreeP;
        Scanner in = new Scanner(System.in) ;
        System.out.println("Input polynom grade: ");
        degreeP = in.nextInt();
        Polynom p = new Polynom(degreeP);
        p.inputCoeff();
        p.output();
        return p;
    }
    private static Polynom sum(Polynom p1, Polynom p2)
    {
        int tempDegree = Math.min(p1.getDegree(), p2.getDegree());
        Polynom tempP = new Polynom(tempDegree);
        for(int i = 0; i < tempDegree + 1; i++)
        {
            tempP.setCoeff(i,p1.getCoeff(i)+p2.getCoeff(i));
        }
        System.out.println("Sum of two polynoms is:");
        tempP.output();
        return tempP;
    }
    private static Polynom difference(Polynom p1, Polynom p2)
    {
        int tempDegree = Math.max(p1.getDegree(), p2.getDegree());
        Polynom tempP = new Polynom(tempDegree);
        for(int i = 0; i < tempDegree + 1; i++)
        {
            tempP.setCoeff(i,p1.getCoeff(i)-p2.getCoeff(i));
        }
        System.out.println("Difference of two polynoms is:");
        tempP.output();
        return tempP;
    }
    private static Polynom multiply(Polynom p1, Polynom p2)
    {
        int tempDegree = p1.getDegree() + p2.getDegree();
        Polynom tempP = new Polynom(tempDegree);
        for(int i = 0; i < p1.getDegree() + 1; i++)
        {
            for(int j =0; j < p2.getDegree() + 1; j++)
            {
                tempP.setCoeff(i + j,tempP.getCoeff(i + j) + p1.getCoeff(i) * p2.getCoeff(j));
            }
        }
        System.out.println("Multiplying of two polynoms is:");
        tempP.output();
        return tempP;
    }
    static class ComplexPolynom extends Polynom
    {
        ComplexPolynom(int degree,int[] coeff,int n)
        {
            super(n);
            super.degree = degree;
            super.coeff = coeff;
            degree = n;
            coeff = new int[degree +1];
            for(int i = 0; i < degree +1; i++)
            {
                coeff[i] = 0;
            }
        }

        ComplexPolynom(int tempDegree)
        {
            super(tempDegree);
        }

        void output()
        {
            for(int i = 0; i < degree + 1; i++)
            {
                System.out.printf("%di*X(%d)", coeff[i], i);
                if(i < degree)
                    System.out.print("+");
            }
            System.out.println();
        }
        private static ComplexPolynom inputPolynom()
        {
            int degreeP;
            Scanner in = new Scanner(System.in) ;
            System.out.println("Input polynom grade: ");
            degreeP = in.nextInt();
            ComplexPolynom c = new ComplexPolynom(degreeP);
            c.inputCoeff();
            c.output();
            return c;
        }
        private static ComplexPolynom sum(ComplexPolynom p1, ComplexPolynom p2)
        {
            int tempDegree = Math.min(p1.getDegree(), p2.getDegree());
            ComplexPolynom tempP = new ComplexPolynom(tempDegree);
            for(int i = 0; i < tempDegree + 1; i++)
            {
                tempP.setCoeff(i, p1.getCoeff(i) + p2.getCoeff(i));
            }
            System.out.println("Sum of two polynoms is:");
            tempP.output();
            return tempP;
        }
        private static ComplexPolynom difference(ComplexPolynom p1, ComplexPolynom p2)
        {
            int tempDeg = Math.max(p1.getDegree(), p2.getDegree());
            ComplexPolynom tempP = new ComplexPolynom(tempDeg);
            for(int i = 0; i < tempDeg+1; i++)
            {
                tempP.setCoeff(i, p1.getCoeff(i) - p2.getCoeff(i));
            }
            System.out.println("Difference of two polynoms is:");
            tempP.output();
            return tempP;
        }
        private static ComplexPolynom multiply(ComplexPolynom p1, ComplexPolynom p2)
        {
            int tempDeg = p1.getDegree()+p2.getDegree();
            ComplexPolynom tempP = new ComplexPolynom(tempDeg);
            for(int i = 0; i < p1.getDegree()+1; i++)
                for (int j = 0; j < p2.getDegree()+1; j++)
                {
                    tempP.setCoeff(i + j, tempP.getCoeff(i + j) + p1.getCoeff(i) * p2.getCoeff(j));
                }
            System.out.println("Multiplying of two polynoms is:");
            tempP.output();
            return tempP;//clone
        }
    }

    public static void main(String[] args)
    {
        int choose = 0;
        ComplexPolynom p1 = null, p2 = null, pResult = null;
        Polynom c1 = null, c2 = null, cResult = null;
        Scanner in = new Scanner(System.in);
        do{
            System.out.println("1. Enter Polynom ");
            System.out.println("2. Get Polynom Sum");
            System.out.println("3. Get Polynom Difference ");
            System.out.println("4. Get Polynom Multiply");
            System.out.println("5. Get Polynoms");
            System.out.println("6. Enter Complex Polynom");
            System.out.println("7. Get Complex Polynom Sum");
            System.out.println("8. Get Complex Polynom Difference ");
            System.out.println("9. Get Complex Polynom  Multiply");
            System.out.println("10. Get Complex Polynoms");
            System.out.println("11. Exit");
            System.out.print("Enter number of menu: ");
            choose = in.nextInt();
            switch(choose)
            {
                case 1:
                    System.out.println("Polynom c1.");
                    c1 = inputPolynom();
                    System.out.println("Polynom c2.");
                    c2 = inputPolynom();
                    break;
                case 2:
                    cResult = sum(c1, c2);
                    break;
                case 3:
                    cResult = difference(c1, c2);
                    break;
                case 4:
                    cResult = multiply(c1, c2);
                    break;
                case 5:
                    System.out.println("Polynom c1.");
                    c1.output();
                    System.out.println("Polynom c2.");
                    c2.output();
                    break;
                case 6:
                    System.out.println("Complex Polynom p1.");
                    p1 = ComplexPolynom.inputPolynom();
                    System.out.println("Complex Polynom p2.");
                    p2 = ComplexPolynom.inputPolynom();
                    break;
                case 7:
                    pResult = ComplexPolynom.sum(p1,p2);
                    break;
                case 8:
                    pResult = ComplexPolynom.difference(p1,p2);
                    break;
                case 9:
                    pResult = ComplexPolynom.multiply(p1,p2);
                    break;
                case 10:
                    System.out.println("Complex Polynom p1.");
                    p1.output();
                    System.out.println("Complex Polynom p2.");
                    p2.output();
                    break;
                case 11:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong Input. Try Again.");
            }
        }while(true);
    }
}


//-------------------------------------------------------------------------------------
