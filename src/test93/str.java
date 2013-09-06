
/*
 * The constructor is used to initialise the start hh:mm:ss
 
 The following class contains 2 functions:
 * func:   string matching 
 
 
 */
package test93;

public class str {

    int duration_hh;
    int duration_mm;
    int duration_ss;

    public str(int hh, int mm, int ss) {

        duration_hh = hh;
        duration_mm = mm;
        duration_ss = ss;

    }

    public int func(String s1, String s2)// string matching function,  returns the index of s1 if string s2 is found in s1
    {
        int pos = -1, l, i, j = 0, l2;
        l = s1.length();
        l2 = s2.length();
        l2--;
        for (i = 0; i < l; i++) {
            if (s1.charAt(i) == ' ') {
                continue;
            }
            if (s1.charAt(i) == s2.charAt(0)) {

                if ((i + l2) < l) {
                    if (s1.substring(i, i + l2 + 1).compareTo(s2) == 0) {
                        pos = i;
                        break;
                    }
                }
            }

        }
        return pos;
    }

    public float func2(int d, int e, int f)//org time   current time
    {
        int a = duration_hh;
        int b = duration_mm;
        int c = duration_ss;
        long tot_s = 3600 * a + 60 * b + c;

        long curr_s= 3600*  d + 60 * e + f;

        return ((float) curr_s / tot_s);
        
    }
}