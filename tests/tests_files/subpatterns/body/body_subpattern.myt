$|Loop(?lst, ?el): ?body

$# For
for ?el in ?lst:
    ?body

$# While
while ?i < len(?lst):
    ?el = ?lst[?i]
    ?body