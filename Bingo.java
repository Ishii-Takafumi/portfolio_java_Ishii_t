import java.util.*;

class Bingo{
    private int num_bingo;
    private boolean is_bingo = false;
    public String[][] generate_bingo()
    {
        Random rand = new Random();
        String[][] bingo = new String[5][5];
        ArrayList<Integer> num_li = new ArrayList<>();
        int x;
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                if(i == 2 && j == 2)
                    bingo[i][j] = " *";
                else
                {
                    x = 1 + rand.nextInt(99);
                    while(num_li.contains(x))
                    {
                        x = 1 + rand.nextInt(99);
                    }
                    if(x >= 10)
                        bingo[i][j] = Integer.toString(x);
                    else
                        bingo[i][j] = " " + Integer.toString(x);
                    num_li.add(x);
                }
            }
        }
        return bingo;
    }
    public void check(int n, String[][]li)
    {
        String num;
        if (n >= 10)
            num = Integer.toString(n);
        else
            num = " " + Integer.toString(n);
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                if(li[i][j].equals(num))
                    {
                        li[i][j] = " *";
                        break;
                    }
            }
        }
    }
    public void judge(String[][] li)
    {
        //横    
        for(int i = 0; i < 5; i++)
        {
            int num1 = 0;
            for(int j = 0; j < 5; j++)
            {
                if(li[i][j].equals(" *"))
                    num1++;
            }
        }

        //縦
        for(int i = 0; i < 5; i++)
        {
            int num1 = 0;
            for(int j = 0; j < 5; j++)
            {   
                if(li[j][i].equals(" *"))
                    num1++;
                if(j == 4 && num1 == 5)
                {
                    is_bingo = true;
                    num_bingo++;
                }
            }
        }

        //斜め
        int num3 = 0; int num4 = 0;
        for(int i = 0; i < 5; i++)
        {
            if(li[i][i].equals(" *"))
                num3++;
            if(li[i][4-i].equals(" *"))
                num4++;
            if(i == 4)
            {
                if(num3 == 5)
                {
                    is_bingo = true;
                    num_bingo++;
                }
                if(num4 == 5)
                {
                    is_bingo = true;
                    num_bingo++;
                }
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        ArrayList<Integer> num_arr = new ArrayList<>();
        System.out.println("ビンゴゲームを始めます");
        Bingo B1 = new Bingo();
        String[][] card1 = B1.generate_bingo();
        int N;
        do{
            System.out.println("何回引きますか?(1以上の数)");
            N = sc.nextInt();
        }while(N <= 0);
        
        for(int i = 0; i < N; i++)
        {
            int num = 1 + rand.nextInt(99);
            while(num_arr.contains(num))
            {
                num = 1 + rand.nextInt(99);
            }
            num_arr.add(num);
            B1.check(num, card1);
        }
        
        B1.judge(card1);
        System.out.println(B1.is_bingo ? "おめでとう!":"残念");
        String YN = "";
        do{
            System.out.println("結果を表示しますか?(\"Y\":はい \"N\":いいえ)");
            YN = sc.next();
        }while(!YN.equals("Y") && !YN.equals("N"));
        if(YN.equals("Y"))
        {
            for(int i = 0; i < 5;i++)
            {
                for(int j = 0; j < 5; j++)
                {
                    System.out.print(card1[i][j]+" ");
                }
                System.out.print("\n");
            }
            System.out.println("Bingo数:"+B1.num_bingo);
        }
    }
}