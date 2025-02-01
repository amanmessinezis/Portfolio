from Connection.Impl_DBConnection import Impl_DBConnection


class SQLHelper:

    # Connect to the database
    def __init__(self):
        self.database = Impl_DBConnection()
        self.__sql: str = None
        """# @AssociationKind Aggregation"""
