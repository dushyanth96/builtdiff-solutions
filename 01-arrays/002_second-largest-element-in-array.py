class Solution:
    def find_runner_up_power_draw(n: int, Arr: list[int]) -> int:
        first = -float('inf')
        second = -float('inf')

        for x in Arr:
            if x > first:
                second = first
                first = x
            elif first > x > second:
                second = x

        return second if second != -float('inf') else -1