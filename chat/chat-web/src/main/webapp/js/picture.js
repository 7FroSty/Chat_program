$(document).ready(function() {
    let readURL = function(input) {
        if (input.files && input.files[0]) {
            let reader = new FileReader();

            reader.onload = function (e) {
                $('#img').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#picture").on('change', function(){
        readURL(this);
    });

    $("#send-message").submit( function(eventObj) {
        $("<input />").attr("type", "hidden")
            .attr("name", "picture")
            .attr("value", $('#img').attr('src') )
            .appendTo("#send-message");
        return true;
    });
});