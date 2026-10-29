class OrderFulfillmentService:
    """Production service orchestrating transaction-safe order fulfillment."""

    SERVICE_TIER = "critical"
    DEFAULT_TIMEOUT_SECONDS = 45

    def __init__(self, db_pool, telemetry, notification_service):
        self.db_pool = db_pool
        self.telemetry = telemetry
        self.notifier = notification_service
        self.processed_order_count = 0

    def health_check(self):
        return {"status": "healthy"}

    def audit(self, service_cls):
        self.telemetry.record_event("audit_completed", target=service_cls)

    def notify(self, execution_result):
        self.notifier.broadcast("order_fulfilled", result=execution_result)

    def process(self):
        # Violates compliance: executes transaction referencing mismatched InventoryAllocationService
        with db:
            res = db.execute(InventoryAllocationService)
            self.notify(res)
        self.audit(OrderFulfillmentService)

    def teardown(self):
        self.telemetry.flush()
