package SOS.TestSuites.BasicSuites;

import SOS.Tests._1_Create_Supplier_New_NewPendingApproval_Emergency_Approved_Reject.Create_Supplier_Status_Approve;
import SOS.Tests._1_Create_Supplier_New_NewPendingApproval_Emergency_Approved_Reject.Create_Supplier_Status_Reject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({Create_Supplier_Status_Approve.class, Create_Supplier_Status_Reject.class})

public class Test_Suite_Approved_Rejected_New_supplier {
}