<script type="text/javascript" src="jquery_002.js"></script>
<script type="text/javascript" src="jquery.validate.min.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
jQuery.validator.addMethod("notEqual", function(value, element, param) {return this.optional(element) || value != param;}, "Please enter your name");

$.validator.addMethod("pwcheck",
function(value, element) {
   return /^[A-Za-z0-9\d=!\-@._*]+$/.test(value);
});
jQuery.validator.addMethod("phoneUS", function(phone_number, element) {
    phone_number = phone_number.replace(/\s+/g, "");
    return this.optional(element) || phone_number.length == 10  && 
    phone_number.match(/^(\+?0-?)?(\([2-9]\d{2}\)|[2-9]\d{2})-?[2-9]\d{2}-?\d{4}$/);
}, "Please specify a valid phone number  ...");

$(document).ready(function() {jQuery.validator.addMethod("lettersonly", function(value, element) {
  return this.optional(element) || /^[a-z ]+$/i.test(value);
}, "Letters only please");

			$("#register_form").validate({
				submitHandler:function(form) 
				{
					SubmittingForm();
				},
				rules: 
				{
					first_name: 
					{
						required: true,
						lettersonly: true			
					},// simple rule, converted to {required:true}
					last_name: 
					{
						required: true,
						lettersonly: true			
					},
					house: 
					{
						required: true
					},
					street:
					{
						required: true
					},
					city:
					{
						required: true,
						lettersonly: true			
					},
					state:
					{
						required: true,
						lettersonly: true			
					},
					designation:
					{
						required: true
					},
					status:
					{
						required: true
					},
					phone_number: 
					{
						required: true,
						
						phoneUS:true 
							
					},
					email: 
					{
						required: true,
					    email: true	
					},
					username: 
					{
						required: true
					},
					password: 
					{
						required: true,
						rangelength: [6,20]
						
					},
					password2: 
					{
						required: true,
						equalTo:'#password1'
					},
					comment: 
					{
						required: true
					}
					},
					messages: 
					{
						comment: "Please enter a comment.",
						password2: "Password Doesn't Matched"
					}
			});	
		});	
</script>
<link type="text/css" href="jquery.datepick.css" rel="stylesheet">
<script type="text/javascript" src="jquery.datepick.js"></script>
<script type="text/javascript">
$(function() {
	$('#t').datepick();
});

function showDate(date) {
	alert('The date chosen is ' + date);
}
</script>
<script type="text/javascript">
$(function() {
	$('#tt').datepick();
});

function showDate(date) {
	alert('The date chosen is ' + date);
}
</script>

 


