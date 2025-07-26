/**
 * Created by toufiq on 9/28/2022.
 */
var lorgOb;
$(function () {
    $(document).ready(function () {
        $('#inputexchRateDiv').attr('hidden', true);
        initPage();
    })

    $('#inputarReceiptClass').on('change', function () {

        arReceiptClassChangeEvent($(this));
    });

    $('#inputarChargeTypes').on('change', function () {

        arChargeTypesChangeEvent($(this));
    });

    $('#inputcustomerLineName').on('input', function () {
        if ($('#inputcustomerLineName').val().length > 1) {
            var displayfieldCust = '#inputcustomerLineName';
            var locOrgId = $('#inputorgHierarchy').val();
            var getvalue = 'name';
            var getDesc = 'siteAddress';
            var indc = 'custArRct';
            var urlvalCust = '/AYE/ACRC/getCustomerLine/' + locOrgId + '/' + $('#inputcustomerLineName').val();

            autocomFuncArRct(urlvalCust,  displayfieldCust,getvalue, getDesc, indc);

            $('#inputcustomerLineName').focus();
        } else {
            $('#inputcustomerLine').val('');
        }

    });

    $('#inputbankLine').on('input',function(){
        if ($('#inputbankLine').val().length > 1) {
            var displayfield = '#inputbankLine';
            var locOrgId = $('#inputorgHierarchy').val();
            var lcashId = $('#inputbankAccountId').val();
            var getvalue = 'name';
            var indc = 'bankArRct';
            var getDesc = 'accountNumber';
            var urlvalBank = '/AYE/ACRC/SearchOrgbankByCashAccount/' + locOrgId + '/'+lcashId+ '/' + $('#inputbankLine').val();
         //alert(urlvalBank);
            autocomFuncArRct(urlvalBank,  displayfield,getvalue, getDesc, indc);
            $('#inputbankLine').focus();
        } else {
            $('#bankLineId').val('');
        }

    })

    $('#inputamount').on('input', function () {
        setBaseAmount();
    });

    })





function autocomFuncArRct(purlval, pdisplayfield, pgetvalue, pgetDesc, pindc) {
    var urlval = purlval;//'/AYE/ACRC/SearchCodeComb/' + bSeg;
    var displayfield = pdisplayfield;//'#toAccountV';
    var getvalue = pgetvalue;//'concatedSegment';
    var getDesc = pgetDesc;//'siteAddress';
    var indc = pindc;//'segment2desc';

    autocomwithobject(urlval, getvalue, displayfield, null, getDesc, indc);

};
function selectedobj(value, ind) {
    if (ind == 'custArRct') {

        SearchCusArObj(value);
    }else if (ind == 'bankArRct') {

        SearchBankArObj(value);
    }
};

function SearchCusArObj(objparms) {
//                                            alert('x');
    var objparmV = objparms;
    $('#inputcustomerLine').val(objparmV.id).trigger("change");
    $('#inputcustomerLineAccId').val(objparmV.accCodeCombinitions.id).trigger("change");
};

function SearchBankArObj(objparms) {
//                                            alert('x');
    var objparmV = objparms;
    $('#bankLineId').val(objparmV.id).trigger("change");
    $('#inputbankAccountId').val(objparmV.accCodeCombinitions.id).trigger("change");
};

function arReceiptClassChangeEvent(p) {
    var crId = p.find(":selected").attr('data-currency');
    console.log(crId);
    var lCashAccId = p.find(":selected").attr('data-cashAcc');
    $('#inputcurrency').val(crId);
    $('#inputbankAccountId').val(lCashAccId);
    $('#inputcurrencyCode').val(p.find(":selected").attr('data-currencyCode'));
    console.log("juuu");

    console.log(lorgOb.currency);
    console.log(lorgOb);

        if((typeof(crId)  != "undefined") &&(lorgOb.currency.id != crId)) {
        $('#inputexchRateDiv').attr('hidden', false);
    }
    $('#inputexchRate').val(lorgOb.currency.defaultExcgangeRate);
    $('#inputbankLine').removeAttr('readonly');
    $('#inputamount').removeAttr('readonly');
}

function arChargeTypesChangeEvent(p) {

    var lChargeAccId = p.find(":selected").attr('data-chargeAcc');
    //alert(lChargeAccId );
    $('#inputchargeAccount').val(lChargeAccId);

}

function setBaseAmount()
{
    var lBaseAmt =0;
    lBaseAmt = $('#inputexchRate').val()*$('#inputamount').val();
    $('#inputbaseAmount').val(lBaseAmt);

}



function initPage()
{


    if (($('#inputcurrency').val().length>0)&&(lorgOb.currency.id !=  $('#inputcurrency').val())) {
        $('#inputexchRateDiv').attr('hidden', false);
    }

    if($('#inputarReceiptClass').val()!="")
    {
        $('#inputbankLine').removeAttr('readonly');
        $('#inputamount').removeAttr('readonly');
    }

}