$&Singleton(?SingletonMeta, ?Singleton)

$# MetaClass
class ?SingletonMeta:
    ?instances = {}

    def __call__(?cls, ?*):
        if ?cls not in ?cls.?instances:
            ?instance = super().__call__(?*)
            ?cls.?instances[?cls] = ?instance
        return ?cls.?instances[?cls]

$# Class
class ?Singleton(metaclass=?SingletonMeta):
    ?