*** Settings ***
Library    ru.stqa.pft.addressbook.rf.AddressBookKeywords
Suite Setup    Init Application Manager
Suite Teardown    Stop Application Manager


*** Test Cases ***
Can Create Group With Valid Data
    ${old_count} =    Get Group Count
    Create Group    Test name    Test header    Test footer
    ${new_count} =    Get Group Count
    ${expected_count} =    Evaluate    ${old_count} + 1
    Should Be Equal As Integers    ${new_count}    ${expected_count}