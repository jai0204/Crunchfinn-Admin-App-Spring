let stateList, countryList, universityList;

async function loadLists (initializeLists) {
    const response = await fetch("/json/stateCity.json");
    stateList = await response.json();
    const response1 = await fetch("/json/countries.json");
    countryList = await response1.json();
    const response2 = await fetch("/json/universities.json");
    universityList = await response2.json();
    initializeLists();
}

function stateOptions(stateIds, mandatory) {
    var stateSelects = [];
    stateIds.forEach(element => {
        stateSelects.push(document.getElementById(element));
    });
    stateSelects.forEach(stateSelect => {
        stateSelect.length = 0;
	    stateSelect.options[0] = new Option('Choose..','');
        if (mandatory) {
            stateSelect.options[0].disabled = true;
        }
	    stateSelect.selectedIndex = 0;
	    stateList.forEach(state => {
            stateSelect.options[stateSelect.length] = new Option(state.name, state.state_code);
        });
    });
}

function cityOptions(cityId, cityIndex, mandatory){
	const citySelect = document.getElementById(cityId);
	citySelect.length = 0;
	citySelect.options[0] = new Option('Choose..','');
    if (mandatory) {
        citySelect.options[0].disabled = true;
    }
	citySelect.selectedIndex = 0;
	if (cityIndex > 0) {
        stateList[cityIndex-1].cities.forEach(city => {
            citySelect.options[citySelect.length] = new Option(city.name, city.name);
        });
    }
}

function countryOptions(countryId) {
    const countrySelect = document.getElementById(countryId);
    countrySelect.length = 0;
    countrySelect.options[0] = new Option('Choose..', '');
    countrySelect.options[0].disabled = false;
    countrySelect.selectedIndex = 0;
    countryList.forEach(country => {
        countrySelect.options[countrySelect.length] = new Option(country.name, country.code);
    });
}

function universityOptions(universityId, universityListId, countryCode) {
    const universityInput = document.getElementById(universityId);
    const universityDataList = document.getElementById(universityListId);
    universityDataList.innerHTML = '';
    if (!countryCode) {
        universityInput.value = '';
        universityInput.disabled = true;
        universityInput.placeholder = 'Choose a country';
        return;
    }
    universityInput.disabled = false;
    universityInput.placeholder = 'Type to search...';
    universityList.forEach(university => {
        if (university.alpha_two_code === countryCode) {
            const option = document.createElement('option');
            option.value = university.name;
            universityDataList.appendChild(option);
        }
    });
}

function allCityOptions(cityId) {
    const cityDataList = document.getElementById(cityId);
    cityDataList.innerHTML = '';
    stateList.forEach(state => {
        state.cities.forEach(city => {
            const option = document.createElement('option');
            option.value = city.name;
            cityDataList.appendChild(option);
        });
    });
}

function indianUniversityOptions(universityId) {
    const universityDataList = document.getElementById(universityId);
    universityDataList.innerHTML = '';
    universityList.forEach(university => {
        if (university.alpha_two_code === 'IN') {
            const option = document.createElement('option');
            option.value = university.name;
            universityDataList.appendChild(option);
        }
    })
}