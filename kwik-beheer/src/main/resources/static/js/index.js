$(document).ready(function() {
    function loadRooms() {
        $.ajax({
            url: 'http://localhost:8080/buildings', 
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                var buildingList = $('#buildings');
                buildingList.empty();
                $.each(data, function(index, building) {
                    buildingList.append('<li>' + building.name + '</li>');
                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error loading buildings:', textStatus, errorThrown);
            }
        });
    }
    loadRooms();
});
