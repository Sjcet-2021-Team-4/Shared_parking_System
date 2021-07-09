<?php

 // include("connection.php");
  session_start();
 
  if(isset($_POST['btnsub']))
  {
	header("location:complete.php");
  }
  
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <style type="text/css">
        .auto-style18 {
            height: 35px;
        }
        .auto-style21 {
            height: 39px;
        }
        .auto-style24 {
            width: 50px;
            height: 30px;
        }
        .auto-style28 {
            width: 34px;
            height: 27px;
        }
        .auto-style29 {
            height: 55px;
        }
        .auto-style30 {
            text-align: right;
            font-weight: 700;
            width: 422px;
            height: 29px;
        }
        .auto-style32 {
            height: 29px;
        }
        .auto-style33 {
            text-align: right;
            font-weight: 700;
            width: 422px;
            height: 30px;
        }
        .auto-style35 {
            height: 30px;
        }
        .auto-style36 {
            text-align: right;
            font-weight: 700;
            width: 422px;
            height: 32px;
        }
        .auto-style38 {
            height: 32px;
        }
        .auto-style39 {
            height: 108px;
        }
        .auto-style40 {
            height: 29px;
            width: 11px;
        }
        .auto-style41 {
            height: 30px;
            width: 11px;
        }
        .auto-style42 {
            height: 32px;
            width: 11px;
        }
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<form name="frm" id="frm" method="post">
<script type="text/javascript">
 <!--
    function printPartOfPage(elementId) {
        var printContent = document.getElementById(elementId);
        var windowUrl = 'about:blank';
        var uniqueName = new Date();
        var windowName = 'Print' + uniqueName.getTime();
        var printWindow = window.open(windowUrl, windowName, 'left=50000,top=50000,width=0,height=0');

        printWindow.document.write(printContent.innerHTML);
        printWindow.document.close();
        printWindow.focus();
        printWindow.print();
        printWindow.close();
    }
    // -->
    </script>
    <div id="printablediv"><fieldset><legend style="text-align: center; font-weight: 700">Payment Details</legend>
       
        <table class="auto-style1">
            <tr>
                <td class="auto-style29"></td>
                <td style="text-align: right" class="auto-style29">
                    <asp:LinkButton ID="LinkButton1" runat="server" OnClientClick="JavaScript: printPartOfPage('printablediv');" Font-Underline="False" ForeColor="White"> <img alt="" class="auto-style28" src="Icons/1391813769_printer.png" /></asp:LinkButton>
                   </td>
                <td class="auto-style29"></td>
            </tr>
            <tr>
                <td class="auto-style18"></td>
                <td class="auto-style18" style="text-align: center; color: #3399FF">Payment Success...</td>
                <td class="auto-style18"></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>
                    <table class="auto-style1">
                        <tr>
                            <td class="auto-style30">Merchant</td>
                            <td class="auto-style40"></td>
                            <td class="auto-style32">
                                &nbsp;<input type="text"  runat="server" value="<?php echo "Shared Parking";?>"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="auto-style33">Date</td>
                            <td class="auto-style41"></td>
                            <td class="auto-style35">
                                &nbsp;<input type="text"  runat="server" value="<?php echo date("Y-m-d");?>"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="auto-style36">Amount</td>
                            <td class="auto-style42"></td>
                            <td class="auto-style38">
                                <input type="text" runat="server" value="<?php echo "Rs ".$_SESSION['amt']."/-"; ?>"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="auto-style36">Transaction ID</td>
                            <td class="auto-style42"></td>
                            <td class="auto-style38">&nbsp; <input type="text" runat="server" value=<?php echo rand(10000,10000000);?>>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" class="auto-style21" style="text-align: center">
                                <input type="submit" runat="server" Text="Continue" name="btnsub" Width="108px" OnClick="Button1_Click" style="font-weight: 700" value="Click here to Complete Transaction"  />
                            </td>
                        </tr>
                    </table>
                </td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="auto-style39"></td>
                <td style="text-align: center" class="auto-style39">&nbsp;&nbsp;&nbsp;
                    <img alt="" class="auto-style24" src="Icons/1391813453_mastercard1.gif" />
                    <img alt="" class="auto-style24" src="Icons/1391813456_visa2.gif" />
                    <img alt="" class="auto-style24" src="Icons/1391813466_westernunion.gif" />
                    <img alt="" class="auto-style24" src="Icons/1391813469_cirrus1.gif" />
                    <img alt="" class="auto-style24" src="Icons/1391813513_visa1.gif" /></td>
                <td class="auto-style39"></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
        </table>
       
    </fieldset></div>
    </form>
</body>
</html>