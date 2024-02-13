package ApacheGuava.guava01;

import com.google.common.base.CharMatcher;
import org.junit.Test;

/**
 * CharMatcher �ַ�ƥ����������ƥ���ַ�,ֻҪ���ʹ�ã����Է��Ӻܴ������
 */
public class CharMatcherTest {
    /**
     * CharMatcher is(final char match)����ʾ����
     * CharMatcher or(CharMatcher other)����ʾ����
     * CharMatcher inRange(final char startInclusive, final char endInclusive)����ʾ in����ĳ����Χ��
     * CharMatcher and(CharMatcher other)����ʾ����
     * CharMatcher isNot(final char match)����ʾ������
     * CharMatcher any()����ʾ�����ַ�
     * <p>
     * boolean matches(char c): ƥ������ַ�
     */
    @Test
    public void test1() {
        //��ʾƥ���ַ� ',' �����ַ� '|' �����ַ� '_'
        CharMatcher charMatcher = CharMatcher.is(',').or(CharMatcher.is('|')).or(CharMatcher.is('_'));
        boolean matches1 = charMatcher.matches(',');
        boolean matches2 = charMatcher.matches('|');
        boolean matches3 = charMatcher.matches('_');
        boolean matches4 = charMatcher.matches('#');
        System.out.println(matches1 + "," + matches2 + "," + matches3 + "," + matches4);
        //true,true,true,false
    }

    @Test
    public void test2() {
        //��ʾƥ�� [a,c] ֮����ַ������ǲ��ܵ��� 'b'
        CharMatcher matcher = CharMatcher.inRange('a', 'c').and(CharMatcher.isNot('b'));
        boolean a = matcher.matches('a');
        boolean b = matcher.matches('b');
        boolean c = matcher.matches('c');
        System.out.println(a + "," + b + "," + c);
        //true,false,true

    }

    /**
     * int countIn(CharSequence sequence)����ȡ�ַ�������ƥ��Ĵ���
     */
    @Test
    public void test3() {
        //�ж��ַ��Ƿ�������
        CharMatcher matcher1 = CharMatcher.inRange('0', '9');
        //�ж��ַ�����ĸ
        CharMatcher matcher2 = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z'));
        //true,false
        System.out.println(matcher1.matches('7') + "," + matcher1.matches('p'));
        //true,false
        System.out.println(matcher2.matches('f') + "," + matcher2.matches(','));

        //��ȡ "abc123sdk897" �ַ��������ֳ��ֵĴ���
        int countIn = matcher1.countIn("abc123sdk897");
        System.out.println(countIn);
        //6
    }

    /**
     * String trimFrom(CharSequence sequence)��ȥ���ַ���������ǰ��ƥ����ַ�
     * String trimLeadingFrom(CharSequence sequence)��ȥ��ͷ��ƥ����ַ�
     * String trimTrailingFrom(CharSequence sequence)��ȥ��β��ƥ����ַ�
     */
    @Test
    public void test4() {
        //ȥ���ַ���ǰ��� \r��\n��\t �Լ��ո��ַ�
        CharMatcher matcher3 = CharMatcher.is('\t').or(CharMatcher.is('\r')).or(CharMatcher.is('\n')).or(CharMatcher.is(' '));
        String text3 = "\r Solr Home \n ";
        String trimFrom = matcher3.trimFrom(text3);
        System.out.println(trimFrom);
        //Solr Home
    }

    /**
     * String removeFrom(CharSequence sequence)��ɾ���ַ���������ƥ����ַ�
     */
    @Test
    public void test5() {
        CharMatcher matcher2 = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z'));
        //ɾ�� "abc123SDK897" �ַ��������е���ĸ
        String removeFrom = matcher2.removeFrom("abc123SDK897");
        System.out.println(removeFrom);
        //123897
    }

    /**
     * String replaceFrom(CharSequence sequence, CharSequence replacement)��ʹ�� replacement �滻 sequence ��ƥ����ַ�
     * CharMatcher anyOf(final CharSequence sequence): ��ʾ sequence �ַ������е������ַ�
     */
    @Test
    public void test6() {
        CharMatcher matcher4 = CharMatcher.is('��').or(CharMatcher.is('��'));
        String text4 = "�����Ż�ͬʱҲ��һ��������֯��";
        String replaceFrom = matcher4.replaceFrom(text4, "xx");
        System.out.println(replaceFrom);
        //��xx�Ż�ͬʱҲ��һ����xx��֯��

        //�޳�Ŀ���ַ����е����������ַ�
        CharMatcher matcher5 = CharMatcher.anyOf("0123456789");
        String replace = matcher5.replaceFrom("����һͳ����221", "");
        System.out.println(replace);
        //����һͳ����

    }

    /**
     * CharMatcher ascii()����ȡ ascii ����ַ�ƥ���������ձ�http://ascii.911cha.com/
     */
    @Test
    public void test7() {
        //��ȡĿ���ַ����� ascii ����ַ����������ֲ����� ascii ��֮�ڣ�
        CharMatcher ascii = CharMatcher.ascii();
        int count = ascii.countIn("12abn_)*77%����");
        //11
        System.out.println(count);
    }

    /**
     * CharMatcher whitespace()����ȡ�հ��ַ�ƥ���������� \r��\n��\t ���ַ�
     */
    @Test
    public void test8() {
        //ȥ��Ŀ���ַ��������пո�
        CharMatcher whitespace = CharMatcher.whitespace();
        String text6 = " һͳ �� �� \r\n\t";
        String replace1 = whitespace.replaceFrom(text6, "");
        //|һͳ����|
        System.out.println("|" + replace1 + "|");
    }

    /**
     * boolean matchesAllOf(CharSequence sequence):
     * 1��Ϊ sequence ��ÿ���ַ����� matches(char c)������ֱ������ false �򵽴��β���� true.
     */
    @Test
    public void test9() {
        //�ж�����
        CharMatcher matcher1 = CharMatcher.inRange('0', '9');
        boolean matchesAllOf1 = matcher1.matchesAllOf("75847584758");
        boolean matchesAllOf2 = matcher1.matchesAllOf("75847584.898");
        System.out.println(matchesAllOf1);
        //true
        System.out.println(matchesAllOf2);
        //false
    }

    /**
     * CharMatcher anyOf(final CharSequence sequence): ��ʾ sequence �ַ������е������ַ�
     * boolean matchesAnyOf(CharSequence sequence)������ַ����������ٰ���һ��ƥ����ַ����򷵻� true
     */
    @Test
    public void test10() {
        //�ж�����
        CharMatcher matcher1 = CharMatcher.anyOf("�й�|����|�ձ�");
        boolean matchesAllOf1 = matcher1.matchesAnyOf("�й�������ǧ��");
        boolean matchesAllOf2 = matcher1.matchesAnyOf("�ձ�ǲ��ʹ");
        boolean matchesAllOf3 = matcher1.matchesAnyOf("���ʹųƸ���");
        System.out.println(matchesAllOf1);
        //true
        System.out.println(matchesAllOf2);
        //true
        System.out.println(matchesAllOf3);
        //false
    }

    /**
     * boolean matchesNoneOf(CharSequence sequence):
     * ����ַ����в�����ƥ����ַ����򷵻� true���൱�� !matchesAnyOf(sequence)
     */
    @Test
    public void test11() {
        //�ж�����
        CharMatcher matcher1 = CharMatcher.anyOf("�й�|����|�ձ�");
        boolean matchesAllOf1 = matcher1.matchesNoneOf("�й�������ǧ��");
        boolean matchesAllOf2 = matcher1.matchesNoneOf("�ձ�ǲ��ʹ");
        boolean matchesAllOf3 = matcher1.matchesNoneOf("���ʹųƸ���");
        //false
        System.out.println(matchesAllOf1);
        //false
        System.out.println(matchesAllOf2);
        //true
        System.out.println(matchesAllOf3);
    }

}