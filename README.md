# CoinTask

FUNCTIONALITY
[x] 1. When	the	app	is	started,	load	and	display	a	list	of	coins
[x] 2. Order	entries	by	name
3. Filter	the	list	based	on	tags.
[x] 4. Display	a	divider	between	each	entry
[x] 5. Display	any	extra	coin	info in	some	kind	of	popup/fragment	when coin entry	is	clicked	based	on	the	second	API	request,	with	said	id as	the	query.
[x] 6. Provide	some	kind	of	refresh	option	that	reloads	the	list
[x] 7. Display	an	error	message	if	the	list	cannot	be	loaded	(e.g.	no	network)
[x] 8. (Extra	credit)	Animate	list	items	(hint	–	Jetpack	Compose	allows	this	easily).

DELIVERABLES
[x] 1. Write	the	app	using	Kotlin	only.
[x] 2. Use	Jetpack	Compose	for	UI.
[x] 3. Use	MVVM	design	pattern,	combined	with	RxJava	or	Coroutines	(remember;	keep	it simple!)
4. Write	unit	tests	against	the	ViewModel	(and	anywhere	you	feel	necessary,	using	i.e.
   Mockito),	so	it’s	tested	that	i.e.	entries	are	ordered	by	name,	etc.

## My Approach
- Worked on making sure the main objectives was completed, however was unsure on how to display the filter information, whether that would be a button on the screen or a constant display.
- The ViewModelTest is incomplete due to issues with mocking the apiService data, the IDE kept suggesting to make the function public so it can be used but I opted against that.
- I also kept experiencing issues with API response limits and encountered delays with testing the data display on the app.
- If I had more time I would work on filtering the names better and making the ViewModelTest work.