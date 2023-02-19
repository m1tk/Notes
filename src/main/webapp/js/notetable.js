history.pushState({}, null, window.location.href.split('?')[0]);

// Delete row on delete button click
$(document).on("click", ".delete", function(){
    var id = $(this).parents("tr").attr("id");
    window.location.href=window.location.href.split('?')[0]+"?delete="+id;
});
// Edit row on edit button click
$(document).on("click", ".edit", function(){		
    var id = $(this).parents("tr").attr("id");
    window.location.href=window.location.href.split('?')[0]+"?edit="+id;
});
// Cancel button
$(document).on("click", ".cancel", function(){
    $(this).parents("tr").find(".add, .edit").toggle();
    $(this).parents("tr").find(".cancel, .delete").toggle();
    var input = $(this).parents("tr").find('input[type="text"]');
    input.each(function(){
        $(this).parent("td").html($(this).val());
    });
});
