/**
 * Created by toufiq on 8/27/2022.
 */
$(function(){
    $('#inputemployeeName').on('input',function(){
        if($('#inputemployeeName').val().length>1)
        {
            var urlval = '/AYE/ACRC/SearchEmployeeByName/'+$('#inputemployeeName').val();
            //  alert(urlval);
            var displayfield = '#inputemployeeName';
            var dbfield = '#inputemployeeInfo';
            var getvalue ='empName'; //'empNo';
            var indc = 'userEmpInfo11';
            var opt = 'empNo';

            autocomwithobject(urlval, getvalue, displayfield, dbfield, opt, indc);
            $('#inputemployeeName').focus();
        }else
        {
            $('#inputemployeeInfo').val('');
        }


    })



})

function selectedobj(value, ind) {
    if (ind == 'userEmpInfo11') {
        $('#inputemployeeInfo').val(value.id).trigger('change');
    }
}
