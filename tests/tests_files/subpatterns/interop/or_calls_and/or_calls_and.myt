$|Transaction(?tx, ?val)

$# ViaExplicit
?$ExplicitTx(?tx, ?val)

$# ViaContext
?$ContextTx(?tx, ?val)

$&ExplicitTx(?tx, ?val)

$# BeginTx
?tx.begin()

$# WriteTx
?tx.write(?val)

$# CommitTx
?tx.commit()

$&ContextTx(?tx, ?val)

$# OpenContext
with ?tx:
    ?tx.write(?val)
