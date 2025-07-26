$(function () {

    $('#inputUserAccessTemplateM').on('input',function (){
        console.log($('#inputUserAccessTemplateM').val());
        autocomTmpltName();
        $('#inputUserAccessTemplateM').focus();
    })


    $('#inputUserMenuM').on('input',function (){
        console.log($('#inputUserMenuM').val());
        autocomMenuName();
        $('#inputUserMenuM').focus();
    })

});



function autocomTmpltName() {
    console.log("running")
    var options = {
        url: 'http://localhost:9091/AYE/templateData',

        getValue: 'tempDescription',
        minCharNumber: 2,

        requestDelay: 100,


        list: {


            onSelectItemEvent: function () {
                var value = $("#inputUserAccessTemplateM").getSelectedItemData().id;
                console.log(value);
                $("#userAccessTemplateM").val(value).trigger("change");
            },
            match: {
                enabled: true
            }
        }
    };
    console.log("running")

    $('#inputUserAccessTemplateM').easyAutocomplete(options);
}

function autocomMenuName() {
    console.log("running")
    var options = {
        url: 'http://localhost:9091/AYE/menuData',

        getValue: 'menuName',
        minCharNumber: 2,

        requestDelay: 100,


        list: {


            onSelectItemEvent: function () {
                var value = $("#inputUserMenuM").getSelectedItemData().id;
                console.log(value);
                $("#userMenuId").val(value).trigger("change");
            },
            match: {
                enabled: true
            }
        }
    };
    console.log("running")

    $('#inputUserMenuM').easyAutocomplete(options);
}



















//
//
// // Define the function to handle the autocompletion
// function autocom() {
//     // Get the input value
//     var inputValue = $('#department').val();
//
//     // Options for EasyAutocomplete
//     var options = {
//         // Add your desired options here
//         // For example:
//         data: ['Department 1', 'Department 2', 'Department 3'], // Replace with your own department data source
//         placeholder: 'Enter Department Name...',
//         getValue: 'text',
//         list: {
//             match: {
//                 enabled: true
//             }
//         }
//     };
//
//     // Apply EasyAutocomplete to the "department" input field
//     $('#department').easyAutocomplete(options);
// }
//
// // Initialize EasyAutocomplete when the document is ready
// $(document).ready(function() {
//     autocom(); // Call the autocom function to set up the initial autocompletion
// });
//
//
// // function autocom() {
// //         var options = {
// //         url: 'http://localhost:9090/departmentData',
// //
// //         getValue: 'name',
// //         minCharNumber: 2,
// //
// //         requestDelay: 100,
// //
// //
// //         list: {
// //
// //
// //             onSelectItemEvent: function () {
// //                 var value = $("#department").getSelectedItemData().id;
// //                 console.log(value);
// //                 $("#departmentId").val(value).trigger("change");
// //             },
// //             match: {
// //                 enabled: true
// //             }
// //         }
// //     };
// //     console.log("running")
// //
// //     $('#departmentId').easyAutocomplete(options);
// // }
//
//
//
// // $(document).ready(function() {
// //     var options = {
// //         url: 'http://localhost:9090/departmentData',
// //
// //         getValue: 'name',
// //         minCharNumber: 2,
// //
// //         requestDelay: 100,
// //
// //         list: {
// //
// //
// //             onSelectItemEvent: function () {
// //                 var value = $("#department").getSelectedItemData().id;
// //                 console.log(value);
// //                 $("#departmentId").val(value).trigger("change");
// //             },
// //             match: {
// //                 enabled: true
// //             }
// //         }
// //     };
// //
// //     $('#departmentId').easyAutocomplete(options);
// //
// //

// //
// //
// //
// // });