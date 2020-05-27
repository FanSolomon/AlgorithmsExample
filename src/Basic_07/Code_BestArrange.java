package Basic_07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲， 会议室不能同时容纳两个项目
 * 的宣讲。 给你每一个项目开始的时间和结束的时间(给你一个数
 * 组， 里面 是一个个具体的项目)， 你来安排宣讲的日程， 要求会
 * 议室进行 的宣讲的场次最多。 返回这个最多的宣讲场次。
 *
 * 贪心策略：看哪个项目最早结束，就安排哪个。然后淘汰掉因为安排了
 * 这个而不能做的项目。然后继续选
 */
public class Code_BestArrange {

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }

    }

    public static int bestArrange(Program[] programs, int start) {
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if (start <= programs[i].start) {
                result++;
                start = programs[i].end;
            }
        }
        return result;
    }
}
