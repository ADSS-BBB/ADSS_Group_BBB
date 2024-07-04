BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "branches" (
	"branchID"	INTEGER,
	"location"	TEXT,
	PRIMARY KEY("branchID")
);
CREATE TABLE IF NOT EXISTS "HRmanagers" (
	"name"	TEXT,
	PRIMARY KEY("name")
);
CREATE TABLE IF NOT EXISTS "contracts" (
	"contractID"	INTEGER,
	"employeeID"	INTEGER,
	"branchID"	INTEGER,
	"salary"	INTEGER,
	"employmentType"	TEXT,
	"startDate"	TEXT,
	PRIMARY KEY("contractID"),
	FOREIGN KEY("branchID") REFERENCES "branches"("branchID"),
	FOREIGN KEY("employeeID") REFERENCES "employees"("employeeID")
);
CREATE TABLE IF NOT EXISTS "bankaccounts" (
	"username"	TEXT,
	"password"	TEXT,
	"balance"	INTEGER,
	"employeeID"	INTEGER,
	PRIMARY KEY("username"),
	FOREIGN KEY("employeeID") REFERENCES "employees"("employeeID")
);
CREATE TABLE IF NOT EXISTS "Roles" (
	"EmployeeID"	INTEGER,
	"BranchId"	INTEGER,
	"role"	TEXT,
	FOREIGN KEY("EmployeeID") REFERENCES "employees"("employeeID"),
	FOREIGN KEY("BranchId") REFERENCES "branches"("branchID")
);
CREATE TABLE IF NOT EXISTS "employees" (
	"employeeID"	INTEGER,
	"employeeName"	TEXT,
	"bankUsername"	TEXT,
	"contractID"	INTEGER,
	"branchID"	INTEGER,
	PRIMARY KEY("employeeID"),
	FOREIGN KEY("branchID") REFERENCES "branches"("branchID"),
	FOREIGN KEY("bankUsername") REFERENCES "bankaccounts"("username"),
	FOREIGN KEY("contractID") REFERENCES "contracts"("contractID")
);
CREATE TABLE IF NOT EXISTS "schedule" (
	"ShiftID"	INTEGER,
	"EmployeeID"	INTEGER,
	"BranchID"	INTEGER,
	"role"	TEXT,
	FOREIGN KEY("BranchID") REFERENCES "branches"("branchID"),
	FOREIGN KEY("ShiftID") REFERENCES "shifts"("shiftID"),
	FOREIGN KEY("EmployeeID") REFERENCES "employees"("employeeID")
);
CREATE TABLE IF NOT EXISTS "shifts" (
	"shiftID"	INTEGER,
	"branchID"	INTEGER,
	"type"	TEXT,
	"minWorkers"	INTEGER,
	"time"	TEXT,
	PRIMARY KEY("shiftID"),
	FOREIGN KEY("branchID") REFERENCES "branches"("branchID")
);
CREATE TABLE IF NOT EXISTS "shiftshistory" (
	"EmployeeID"	INTEGER,
	"BranchID"	INTEGER,
	"ShiftID"	INTEGER,
	FOREIGN KEY("ShiftID") REFERENCES "shifts"("shiftID"),
	FOREIGN KEY("BranchID") REFERENCES "branches"("branchID"),
	FOREIGN KEY("EmployeeID") REFERENCES "employees"("employeeID")
);
CREATE TABLE IF NOT EXISTS "weeklyShifts" (
	"EmployeeID"	INTEGER,
	"BranchID"	INTEGER,
	"ShiftID"	INTEGER,
	FOREIGN KEY("EmployeeID") REFERENCES "employees"("employeeID"),
	FOREIGN KEY("ShiftID") REFERENCES "shifts"("shiftID"),
	FOREIGN KEY("BranchID") REFERENCES "branches"("branchID")
);
COMMIT;
