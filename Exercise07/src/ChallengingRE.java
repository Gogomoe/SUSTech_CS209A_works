import java.util.regex.Pattern;

public class ChallengingRE {

    public static void main(String[] args) {

        System.out.println("except 11 or 111");

        Pattern a = Pattern.compile("(?!^1{2,3}$)[01]+");

        System.out.println("11 : " + a.matcher("11").matches());
        System.out.println("111 : " + a.matcher("111").matches());
        System.out.println("100 : " + a.matcher("100").matches());

        System.out.println("1 in every odd-number bit position");

        Pattern b = Pattern.compile("1([01]1)*[01]?");

        System.out.println("1 : " + b.matcher("1").matches());
        System.out.println("101 : " + b.matcher("101").matches());
        System.out.println("100 : " + b.matcher("100").matches());
        System.out.println("1010 : " + b.matcher("1010").matches());

        System.out.println("at least two 0s and at most one 1");

        Pattern c = Pattern.compile("(1?0{2,})|(0+1?0+)|(0{2,}1?)");

        System.out.println("000 : " + c.matcher("000").matches());
        System.out.println("100 : " + c.matcher("100").matches());
        System.out.println("101 : " + c.matcher("101").matches());
        System.out.println("010 : " + c.matcher("010").matches());
        System.out.println("1010 : " + c.matcher("1010").matches());

        System.out.println("no two consecutive 1s");

        Pattern d = Pattern.compile("1?(0+1)*0*");

        System.out.println("001 : " + d.matcher("001").matches());
        System.out.println("110 : " + d.matcher("110").matches());
        System.out.println("101 : " + d.matcher("101").matches());
        System.out.println("010 : " + d.matcher("010").matches());
        System.out.println("1110 : " + d.matcher("1110").matches());

    }

}
