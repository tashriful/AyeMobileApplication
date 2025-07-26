/**
 * Created by toufiq on 9/28/2022.
 */
var lorgOb;
$(function () {
    $('#inputreceiptNumber').on('input',function(){
        if($('#inputreceiptNumber').val().length>1)
        {
            var displayfieldCust = '#inputreceiptNumber';
            var locOrgId = $('#inputorgHierarchy').val();
            var getvalue = 'receiptNumber';
            var getDesc = 'null';
            var indc = 'arRecceiptNumberS';
            var urlvalRct = '/AYE/ACRC/SearchOrgReceiptNumber/' + locOrgId + '/' + $('#inputreceiptNumber').val();
            //alert(urlvalRct);
            autocomFuncArRctS(urlvalRct,  displayfieldCust,getvalue, getDesc, indc);

            $('#inputreceiptNumber').focus();
        } else {
            $('#inputcollectionId').val('');
        }

    });



    //$('#inputarReceiptClass').on('change', function () {
    //
    //    arReceiptClassChangeEvent($(this));
    //});
    //
    //$('#inputarChargeTypes').on('change', function () {
    //
    //    arChargeTypesChangeEvent($(this));
    //});

    $('#inputcustomerLineName').on('input', function () {
        if ($('#inputcustomerLineName').val().length > 1) {
            var displayfieldCust = '#inputcustomerLineName';
            var locOrgId = $('#inputorgHierarchy').val();
            var getvalue = 'name';
            var getDesc = 'siteAddress';
            var indc = 'custArRctS';
            var urlvalCust = '/AYE/ACRC/getCustomerLine/' + locOrgId + '/' + $('#inputcustomerLineName').val();

            autocomFuncArRctS(urlvalCust,  displayfieldCust,getvalue, getDesc, indc);

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
            //var urlvalBank = '/AYE/ACRC/SearchOrgbankByCashAccount/' + locOrgId + '/'+lcashId+ '/' + $('#inputbankLine').val();
            var urlvalBank = '/AYE/ACRC/SearchSource/CM/' + locOrgId + '/' + $('#inputbankLine').val();
         //alert(urlvalBank);
            autocomFuncArRctS(urlvalBank,  displayfield,getvalue, getDesc, indc);
            $('#inputbankLine').focus();
        } else {
            $('#bankLineId').val('');
        }

    })

    $('#inputglHeaderDoc').on('input',function(){
        if ($('#inputglHeaderDoc').val().length > 1) {
            var displayfield = '#inputglHeaderDoc';
            var locOrgId = $('#inputorgHierarchy').val();
            var getvalue = 'documentNumber';
            var indc = 'arRctGlDocNumberS';
            var getDesc = 'date';
            var urlvalGlDoc = '/AYE/ACRC/getRctDocNum/' + locOrgId ;
         //alert(urlvalBank);
            autocomFuncArRctS(urlvalGlDoc,  displayfield,getvalue, getDesc, indc);
            $('#inputglHeaderDoc').focus();
        } else {
            $('#inputglHeader').val('');
        }

    })

    $('#inputamount').on('input', function () {
        setBaseAmount();
    });

    })





function autocomFuncArRctS(purlval, pdisplayfield, pgetvalue, pgetDesc, pindc) {
    var urlval = purlval;//'/AYE/ACRC/SearchCodeComb/' + bSeg;
    var displayfield = pdisplayfield;//'#toAccountV';
    var getvalue = pgetvalue;//'concatedSegment';
    var getDesc = pgetDesc;//'siteAddress';
    var indc = pindc;//'segment2desc';

    autocomwithobject(urlval, getvalue, displayfield, null, getDesc, indc);

};
function selectedobj(value, ind) {
    if (ind == 'custArRctS') {

        SearchCusArSObj(value);
    }else if (ind == 'bankArRct') {

        SearchBankArObj(value);
    }else if (ind == 'arRecceiptNumberS') {

        SearchArReceitSObj(value);
    }else if (ind == 'arRctGlDocNumberS') {

        SearchArGlHeaderSObj(value);
    }
};

function SearchArReceitSObj(objparms) {
    var objparmV = objparms;
    $('#inputcollectionId').val(objparmV.id).trigger("change");
};

function SearchCusArSObj(objparms) {
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

function SearchArGlHeaderSObj(objparms) {
//                                            alert('x');
    var objparmV = objparms;
    $('#inputglHeader').val(objparmV.id).trigger("change");

};

function arReceiptClassChangeEvent(p) {
    var crId = p.find(":selected").attr('data-currency');

    var lCashAccId = p.find(":selected").attr('data-cashAcc');
    $('#inputcurrency').val(crId);
    $('#inputbankAccountId').val(lCashAccId);
    $('#inputcurrencyCode').val(p.find(":selected").attr('data-currencyCode'));

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