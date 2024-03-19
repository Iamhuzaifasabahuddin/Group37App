function openTab(tabName, tabId) {
    var tabs = document.querySelectorAll('.nav-link');
    tabs.forEach(function(tab) {
        tab.classList.remove('active');
    });
    document.getElementById(tabId).classList.add('active');

    var i;
    var x = document.getElementsByClassName("tab");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }

    document.getElementById(tabName).style.display = "block";
}

if (userLeague === "Bronze Scholar") {
    openTab('bronze', 'bronze-tab');
} else if (userLeague === "Silver Sage") {
    openTab('silver', 'silver-tab');
} else if (userLeague === "Gold Guru") {
    openTab('gold', 'gold-tab');
} else if (userLeague === "Platinum Prodigy") {
    openTab('platinum', 'platinum-tab');
} else if (userLeague === "Titanium Titan") {
    openTab('titanium', 'titanium-tab');
} else if (userLeague === "Elysium") {
    openTab('elysium', 'elysium-tab');
} else {
    openTab('bronze', 'bronze-tab');
}