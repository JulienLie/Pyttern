?$ServiceClass(?Service):
    ?$AuditedMethod(?method, ?Service):
        ?$SafeTransaction(?tx):
            ?$NoDirectCommit(?tx)
            ?res = ?tx.execute(?Service)
            self.notify(?res)
