$|Loop(?i, ?v): ?body

$# For
for ?i in ?v:
    ?body

$# While
while ?i < len(?v):
    ?body
    ?i += 1
