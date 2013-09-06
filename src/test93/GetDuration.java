
/*
 The following class contains only one function which accepts times say t1(hh1:mm1:ss1) and t2(hh2:mm2:ss2)
 it works fine if and only if t2 IS GREATER than t1
 It finds the difference (t2-t1) and returns the difference(as a string) in the form of hh:mm:ss
 Note : If t2 is less than t1 it returns empty string.
 */
package test93;

public class GetDuration {

    public String func(String hh1, String mm1, String ss1, String hh2, String mm2, String ss2) {
        int h1 = Integer.parseInt(hh1);
        int m1 = Integer.parseInt(mm1);
        int s1 = Integer.parseInt(ss1);
        int h2 = Integer.parseInt(hh2);
        int m2 = Integer.parseInt(mm2);
        int s2 = Integer.parseInt(ss2);
        String str = "";
        int diff_sec, diff_min;
        diff_sec = s2 - s1;
        if (diff_sec < 0) {
            m2--;
            diff_sec += 60;
        }
        diff_min = m2 - m1;
        if (diff_min < 0) {
            h2--;
            diff_min += 60;
        }

        str = "" + (h2 - h1) + ":" + diff_min + ":" + diff_sec;

        if (h2 < h1) {
            str = "";
        }
        return str;
    }
}