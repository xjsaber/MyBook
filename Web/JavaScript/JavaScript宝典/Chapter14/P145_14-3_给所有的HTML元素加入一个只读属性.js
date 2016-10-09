<script type="text/javascript">
if(HTMLElement) {
	HTMLElement.prototype._defineGetter_("childNodeDetail", function() {
		var result = {elementNodes:0, textNodes:0 }
		for (var i=0; i<this.childNodes.lenght; i++) {
			switch (this.childNodes[i].nodeType) {
				case 1:
					result.elementNodes++;
					break;
				case 3:
					result.textNodes++;
					break;
			}
		}
		return result;
	})
}
</script>