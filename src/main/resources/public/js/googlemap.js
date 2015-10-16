var Map = {
       __marker : null,
       __markers : [],
       __restaurants : [],
       __addresses : [],
       __map : null,


      initialize : function() {
        var portland = { lat : 45.5404527, lng : -122.673812 };

        Map.__map = new google.maps.Map(document.getElementById('map'), {
          zoom: 7,
          center: portland,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        })
      },

      getSelectedCategoryAddresses : function() {
        Map.__restaurants = document.getElementsByClassName('restaurant-address');
        for ( var i = 0; i < Map.__restaurants.length; i++ ) {
          Map.__addresses.push(Map.__restaurants[i].getAttribute('data-address'));
        }
        return Map.__addresses;
      },


      createMarkers : function (addresses) {
        var geocoder = new google.maps.Geocoder();
        for (var i = 0; i < addresses.length; i++) {
          var address = addresses[i];
          geocoder.geocode( { 'address' : address }, function(results, status) {
            debugger;
            if(status === google.maps.GeocoderStatus.OK) {
              var lat = results[0].geometry.location.lat();
              var lng = results[0].geometry.location.lng();
              var latlng = [lat, lng];
              Map.__locations.push(Map.latlng);

            } else {
              alert('Geocode was not successful for the following reason: ' + status);
            }
          });
        }
      },


      setMapOnRestaurantMarkers : function(map) {
        for (var i = 0; i < Map.__markers.length; i++) {
          Map.__markers[i].setMap(map);
        }
      },

      deleteMarkers : function() {
        Map.setMapOnRestaurantMarkers(null);
        Map.__markers = [];
        Map.__addresses = [];
        Map.__restaurants = [];
      }

    };//end of Map
