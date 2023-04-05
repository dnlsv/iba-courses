import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        System.out.println(System.getenv("LOAD_PATH"));
    }

    public static String rgb(int r, int g, int b) {
        if (r < 0)
            r = 0;
        if (r > 255)
            r = 255;
        if (g < 0)
            g = 0;
        if (g > 255)
            g = 255;
        if (b < 0)
            b = 0;
        if (b > 255)
            b = 255;

        Color your_color = new Color(r, g, b);
        return Integer.toHexString(your_color.getRGB()).substring(2).toUpperCase();
    }

    public static String meeting(String s) {
        List<String> list = Arrays.asList(s.toUpperCase().split(";"));

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.split(":")[1] + o1.split(":")[0])
                    .compareTo(o2.split(":")[1] + o2.split(":")[0]);
            }
        });
        String str = "";
        for (String value : list) {
            str += "(" + value.split(":")[1] + ", " + value.split(":")[0] + ")";
        }
        return str;
    }

    public static String encryptThis(String text) {
        String[] arr = text.split(" ");
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() > 2) {
                arr[i] = (int) arr[i].charAt(0) + arr[i].charAt(arr[i].length() - 1) +
                    arr[i].substring(2, arr[i].length() - 1) + arr[i].charAt(1);
            } else {
                arr[i] = (int) arr[i].charAt(0) + arr[i].substring(1);
            }
            str += arr[i] + " ";
        }
        return str.trim();
    }

    public static int solution(int number) {
        if (number < 0)
            return 0;
        int sum = 0;
        for (int i = 3; i < number; i++) {
            if (i % 3 == 0 && i % 5 != 0)
                sum += i;
            if (i % 5 == 0)
                sum += i;
        }
        return sum;
    }

    public static String spinWords(String sentence) {
        String[] arr = sentence.split(" ");
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() >= 5) {
                arr[i] = new StringBuilder(arr[i]).reverse().toString();
            }
            str += arr[i] + " ";
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    public static int persistence(int num) {
        if (num < 10)
            return 0;
        String str = Integer.toString(num);
        int counter = 0;
        while (true) {
            int number = 1;
            for (int i = 0; i < str.length(); i++) {
                number = number * Integer.parseInt(str.charAt(i) + "");
            }
            counter++;
            if (number < 10)
                break;
            str = Integer.toString(number);
        }
        return counter;
    }

    public static boolean smallEnough(int[] a, int limit) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > limit)
                return false;
        }
        return true;
    }

    public static List<Integer> evenNumbers(List<Integer> array, int number) {
        List<Integer> list = new ArrayList<>();
        int j = 0;
        for (int i = array.size() - 1; i >= 0; i--) {
            if (array.get(i) % 2 == 0) {
                j++;
                list.add(array.get(i));
                if (j == number)
                    break;
            }
        }
        List<Integer> list2 = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            list2.add(list.get(i));
        }
        for (int i = 0; i < list2.size(); i++) {
            System.out.println(list2.get(i));
        }
        return list2;
    }

    public static String solve(String s) {
        int counterUpperCase = 0;
        int counterLowerCase = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 65 && s.charAt(i) <= 90)
                counterUpperCase++;
            else
                counterLowerCase++;
        }

        if (counterLowerCase >= counterUpperCase)
            return s.toLowerCase();
        else
            return s.toUpperCase();
    }

    public static int getCount(String str) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'o' || str.charAt(i) == 'i' ||
                str.charAt(i) == 'e' || str.charAt(i) == 'u')
                counter++;
        }
        return counter;
    }

    public static String Kata(String word) {
        if (word.length() % 2 != 0) {
            return word.substring(word.length() / 2, word.length() / 2 + 1);
        } else
            return word.substring(word.length() / 2 - 1, word.length() / 2 + 1);
    }

    public static boolean isMerge(String s, String part1, String part2) {
        if (s.length() != part1.length() + part2.length()) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        if (part1.length() != 0 && part1.charAt(0) == s.charAt(0) && isMerge(s.substring(1), part1.substring(1), part2)) {
            return true;
        }
        if (part2.length() != 0 && part2.charAt(0) == s.charAt(0) && isMerge(s.substring(1), part1, part2.substring(1))) {
            return true;
        }
        return false;
    }

    public static boolean g(int i, int j, String s, String part1, String part2) {

        if (i == part1.length() && j == part2.length())
            return true;

        if (i > part1.length() || j > part2.length())
            return false;

        if (part1.charAt(i) == s.charAt(i + j) && part2.charAt(j) != s.charAt(i + j))
            return g(i + 1, j, s, part1, part2);

        else if (part1.charAt(i) != s.charAt(i + j) && part2.charAt(j) == s.charAt(i + j))
            return g(i, j + 1, s, part1, part2);

        else if (part1.charAt(i) == s.charAt(i + j) && part2.charAt(j) == s.charAt(i + j))
            return g(i + 1, j, s, part1, part2) || g(i, j + 1, s, part1, part2);

        return false;
    }

    public static String formatDuration(int seconds) {
        if (seconds == 0)
            return "now";

        int years = (int) (seconds / (60 * 60 * 24 * 365));
        int days = (int) (seconds / (60 * 60 * 24) % 365);
        int hours = (int) (seconds / (60 * 60) % 24);
        int minutes = ((int) (seconds / 60)) % 60;
        int _seconds = seconds % 60;

        String strYears = "";
        if (years > 1)
            strYears = years + " years";
        if (years == 1)
            strYears = years + " year";

        String strDays = "";
        if (days > 1)
            strDays = days + " days";
        if (days == 1)
            strDays = days + " day";

        String strHours = "";
        if (hours > 1)
            strHours = hours + " hours";
        if (hours == 1)
            strHours = hours + " hour";

        String strMinutes = "";
        if (minutes > 1)
            strMinutes = minutes + " minutes";
        if (minutes == 1)
            strMinutes = minutes + " minute";

        String strSeconds = "";
        if (_seconds > 1)
            strSeconds = _seconds + " seconds";
        if (_seconds == 1)
            strSeconds = _seconds + " second";

        List<String> strArr = new ArrayList<>();
        if (!strYears.equals(""))
            strArr.add(strYears);
        if (!strDays.equals(""))
            strArr.add(strDays);
        if (!strHours.equals(""))
            strArr.add(strHours);
        if (!strMinutes.equals(""))
            strArr.add(strMinutes);
        if (!strSeconds.equals(""))
            strArr.add(strSeconds);

        String str = "";
        if (strArr.size() != 1) {
            for (int i = 0; i < strArr.size(); i++) {
                if (i == strArr.size() - 2) {
                    str += strArr.get(i) + " and " + strArr.get(i + 1);
                    break;
                }
                str += strArr.get(i) + ", ";
            }
        } else
            str = strArr.get(0);

        return str;
    }

    public static String Factorial(int n) {
        if (n < 1)
            return null;
        long result = 1;
        for (long i = 1; i <= n; i++) {
            result = result * i;
        }
        return Long.toString(result);
    }

    public static int zeros(int n) {
        if (n <= 4) {
            return 0;
        } else {
            int counterTwo = 0;
            int counterFive = 0;
            int counter = 0;
            for (int i = 1; i <= n; i++) {
                String str = Integer.toString(i);
                if (str.charAt(str.length() - 1) == '0') {
                    for (int j = str.length() - 1; j >= 0; j--) {
                        if (str.charAt(j) == '0')
                            counter++;
                    }
                } else {
                    if (i % 2 == 0)
                        counterTwo++;
                    if (i % 5 == 0)
                        counterFive++;
                }
            }
            return Math.min(counterTwo, counterFive) + counter;
        }
    }

    public static String pigIt(String str) {
        String[] arr = str.split(" ");
        String result = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() != 1) {
                arr[i] = arr[i].substring(1) + arr[i].charAt(0) + "ay";
            } else if (
                (arr[i].charAt(0) >= 65 && arr[i].charAt(0) <= 90) ||
                    (arr[i].charAt(0) >= 97 && arr[i].charAt(0) <= 122)
            ) {
                arr[i] = arr[i] + "ay";
            }
            result = result + arr[i] + " ";
        }
        return result.trim();
    }

    public static char findMissingLetter(char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] + 1 != array[i + 1])
                return (char) (array[i] + 1);
        }
        return ' ';
    }

    public static int[] isPerfectPower(int n) {

        for (int i = 2; i <= n / 2; i++) {
            double result = Math.log(n) / Math.log(i);
            DecimalFormat decimalFormat = new DecimalFormat("#.#####");
            result = Double.parseDouble(decimalFormat.format(result).replace(",", "."));
            if (result % 1 == 0) {
                int[] a = new int[2];
                a[0] = i;
                a[1] = (int) result;
                return a;
            }
        }
        return null;
    }

    public static int[] isPerfectPowerV2(int n) {
        double d = (int) Math.sqrt(n);
        if (d * d == n) {
            int[] a = new int[2];
            a[0] = (int) d;
            a[1] = 2;
            return a;
        }
        for (int i = n / 2; i >= 2; i--) {
            int number = n;
            int j = 0;
            while (true) {
                if (number % i == 0) {
                    j++;
                    number = number / i;
                    if (number == 1) {
                        int[] a = new int[2];
                        a[0] = i;
                        a[1] = j;
                        return a;
                    }
                } else
                    break;
            }
        }
        return null;
    }

    public static String makeReadable(int seconds) {
        int second = seconds % 60;
        int minute = (seconds / 60) % 60;
        int hour = (seconds / 60) / 60;

        String hourStr = Integer.toString(hour);
        String minuteStr = Integer.toString(minute);
        String secondStr = Integer.toString(second);

        if (second % 10 == second)
            secondStr = '0' + secondStr;
        if (minute % 10 == minute)
            minuteStr = '0' + minuteStr;
        if (hour % 10 == hour)
            hourStr = '0' + hourStr;

        return hourStr + "." + minuteStr + "." + secondStr;
    }

    public static String high(String s) {
        return Arrays.stream(s.split(" ")).min(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int sum1 = 0;
                int sum2 = 0;
                for (int i = 0; i < o1.length(); i++) {
                    sum1 += o1.charAt(i) - 96;
                }
                for (int i = 0; i < o2.length(); i++) {
                    sum2 += o2.charAt(i) - 96;
                }
                return sum2 - sum1;
            }
        }).get();
    }

    public static String order(String words) {
        List<String> strArr = Stream.of(words.split(" ")).sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1.replaceAll("[^0-9]", "")) -
                    Integer.parseInt(o2.replaceAll("[^0-9]", ""));
            }
        }).collect(Collectors.toList());

        String str = "";
        for (String item : strArr) {
            str = str + " " + item;
        }

        return str.trim();
    }

    public static int[] arrayDiff(int[] a, int[] b) {
        for (int i = 0; i < b.length; i++) {
            int finalI = i;
            a = Arrays.stream(a).filter(el2 -> el2 != b[finalI]).toArray();
        }
        return a;

    }

    public static int[] arrayDiffV2(int[] a, int[] b) {
        if (a.length == 0 || b.length == 0)
            return a;
        List<Integer> arr1 = new ArrayList<>();
        for (int j : a) {
            arr1.add(j);
        }
        List<Integer> arr2 = new ArrayList<>();
        for (int j : b) {
            arr2.add(j);
        }

        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr2.size(); j++) {
                if (arr1.get(i) == arr2.get(j)) {
                    arr1.remove(i);
                    i--;
                }
            }
        }
        int c[] = new int[arr1.size()];
        for (int i = 0; i < arr1.size(); i++) {
            c[i] = arr1.get(i);
        }
        return c;
    }

    public static String camelCase(String input) {
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) >= 65 && input.charAt(i) <= 90 && input.charAt(i - 1) != ' ')
                input = input.substring(0, i) + " " + input.substring(i);
        }
        return input;
    }

    public static boolean scramble(String str1, String str2) {
        int count = 0;
        outer:
        for (int i = 0; i < str2.length(); i++) {
            for (int j = 0; j < str1.length(); j++) {
                if (str2.charAt(i) == str1.charAt(j)) {
                    count++;
                    str1 = str1.substring(0, j) + str1.substring(j + 1, str1.length());
                    continue outer;
                }
            }
        }
        if (count == str2.length())
            return true;
        else
            return false;
    }

    public static String whoLikesIt(String... names) {
        if (names.length == 0)
            return "no one likes this";
        if (names.length == 1)
            return names[0] + " likes this";
        if (names.length == 2)
            return names[0] + " and " + names[1] + " like this";
        if (names.length == 3)
            return names[0] + ", " + names[1] + " and " + names[2] + " like this";
        if (names.length >= 4)
            return names[0] + ", " + names[1] + " and " + (names.length - 2) + " others like this";
        return "";
    }
}