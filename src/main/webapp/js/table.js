history.pushState({}, null, window.location.href.split('?')[0]);

// Add row on add button click
$(document).on("click", ".add", function(){
    var empty = false;
    var input = $(this).parents("tr").find('input[type="text"]');
    var id    = $(this).parents("tr").attr("id");
    input.each(function(){
        if(!$(this).val()){
            $(this).addClass("error");
            empty = true;
        } else{
            $(this).removeClass("error");
        }
    });
    $(this).parents("tr").find(".error").first().focus();
    if(!empty){
        input.each(function(){
            window.location.href=window.location.href.split('?')[0]+"?type=edit&id="+id+"&new="+$(this).val()
        });			
    }
});
// Edit row on edit button click
$(document).on("click", ".edit", function(){		
    $(this).parents("tr").find("td:not(:last-child)").each(function(){
        $(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
    });		
    $(this).parents("tr").find(".add, .edit").toggle();
    $(this).parents("tr").find(".cancel, .delete").toggle();
    $(".add-new").attr("disabled", "disabled");
});
// Delete row on delete button click
$(document).on("click", ".delete", function(){
    var id = $(this).parents("tr").attr("id");
    window.location.href=window.location.href.split('?')[0]+"?type=delete&id="+id;
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
