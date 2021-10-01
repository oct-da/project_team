$(function(){
        $('.sub').hide();

        $('.title').click(function() {
            $('.sub').each(function(i) {
                // console.log('index : '+i);
                if ($(this).css('display') == 'block') {
                    // $(this).hide();
                    $(this).slideUp('fast');
                }

            });
            // $(this).next().show();
            $(this).next().slideDown('fast');
        });
});