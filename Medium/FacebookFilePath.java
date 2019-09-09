/*
Facebook - File Path


类似file system，没在地里见过这道题，可能是lz太懒了没怎么刷面经，面试的时候理解题目花了一小会。
input = [/a/b/c,/a/b,/x/y]
output =‍‍‍‍‌‍‌‍‍‍‌‍‍‍‌‍2 [/a/b,/x/y]

input = [/a/b/c,/a/c]
output = [/a/b/c,a/c]
input是list of file path，如果这个path的prefix和input里的某一个file path一样，就不需要把这个path放进output里。顺序是unordered.
*/